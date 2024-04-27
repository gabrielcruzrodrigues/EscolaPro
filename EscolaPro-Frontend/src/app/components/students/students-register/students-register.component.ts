import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FinancialResponsibleData } from 'src/app/domain/student/FinancialResponsibleData';
import { StudentData } from 'src/app/domain/student/StudentData';
import { CepService } from 'src/app/services/externalApi/cep-service.service';
import { FormStudentsDataService } from 'src/app/services/form-students-data.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CofirmOutsideClick } from 'src/app/utils/ConfirmOutsideClick';


@Component({
  selector: 'app-students-register',
  templateUrl: './students-register.component.html',
  styleUrls: ['./students-register.component.scss']
})
export class StudentsRegisterComponent implements OnInit{
  formData = new FormData();
  formDataFinancialResponsible = new FormData();
  studentsData = new StudentData();
  financialResponsibleData = new FinancialResponsibleData();
  image: boolean = false;
  rg: boolean = false;
  cpf: boolean = false;
  proofOfAddress: boolean = false;
  
  form: FormGroup;

  constructor(
    private studentService: FormStudentsDataService,  
    private router: Router,
    private cepService: CepService,
    private confirm: CofirmOutsideClick,
    private fb: FormBuilder) {
      this.form = this.fb.group({
        name: ['', Validators.required],
        cpf: ['', Validators.required],
        identity: ['', Validators.required],
        dateOfBirth: ['', Validators.required],
        phone: ['', Validators.required],
        email: ['', Validators.required],
        cep: ['', Validators.required],
        address: ['', Validators.required],
        city: ['', Validators.required],
        neighborhood: ['', Validators.required],
        numberHouse: ['', Validators.required],
        nationality: ['', Validators.required],
        sex: ['', Validators.required],
        state: ['', Validators.required],
        country: ['', Validators.required],
        // imageProfile: ['', Validators.required],
        // rgFile: ['', Validators.],
        // cpfFile: ['', Validators.required],
        // proofOfAddress: ['', Validators.required],
        responsible: ['', Validators.required],
        emailPersonResponsible: ['', Validators.required],
        father: ['', Validators.required],
        mother: ['', Validators.required],
        naturalness: ['', Validators.required],
        financialResponsible: ['', Validators.required]
      });
    }

  ngOnInit(): void {
    this.form.valueChanges.subscribe((value) => {
      this.studentsData = {...value};
    });
  }

  handleOutsideClick(event: Event): void {
    this.confirm.handleOutsideClickFirstStage(event);
  }

  onFileSelected(event: any): void  {
    const file: File = event.target.files[0];
    if (file) {
      this.formData.append('imageProfile', file, file.name);
      this.image = true;
    }
  }

  onRgSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.formData.append('rgFile', file, file.name);
      this.formDataFinancialResponsible.append('rgFile', file, file.name);
      this.rg = true;
    }
  }

  onCpfSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.formData.append('cpfFile', file, file.name);
      this.formDataFinancialResponsible.append('cpfFile', file, file.name);
      this.cpf = true;
    }
  }

  onProofOfAddressSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.formData.append('proofOfAddress', file, file.name);
      this.formDataFinancialResponsible.append('proofOfAddress', file, file.name);
      this.proofOfAddress = true;
    }
  }

  sendData(action: 'pending' | 'register'): void {
    const situation = action === 'pending' ? 'PENDENTE' : 'MATRICULADO';
    this.studentsData.situation = situation;
    this.setStudentDataInFormData();
    
    if (this.form.valid) {
      this.studentService.submitStudentData(action, this.formData).subscribe({
        next: (response: HttpResponse<any>) => this.verifyResponse(response),
        error: (error: HttpErrorResponse) => this.verifyResponse(error)
      });
    } else {
      const invalidFields = this.getInvalidFields();
      alert(`Por favor, preencha corretamente os seguintes campos: ${invalidFields.join(', ')}`);
    }
  }

  getInvalidFields(): string[] {
    const invalidFields: string[] = [];
    Object.keys(this.form.controls).forEach(key => {
      const control = this.form.get(key);
      if (control && control.invalid) {
        invalidFields.push(key);
      }
    });
    return invalidFields;
  }

  setStudentDataInFormData() {
    Object.keys(this.studentsData).forEach(key => {
      const safeKey = key as keyof StudentData;
      this.formData.append(safeKey, this.studentsData[safeKey]);
    })
  }

  verifyCepStudent(event: Event) {
    const query = (event.target as HTMLInputElement).value;
    if (query.length === 8) {
      alert("buscando cep...");
      this.cepService.verifyCep(query).subscribe({
        next: (response: any) => this.populateAddressFields(response),
        error: (error) => alert("Este cep não é válido.")
      });
    }
  }

  populateAddressFields(response: any): void {
    if (response.cep) {
      const { cep, state, city, neighborhood, street } = response;
      this.form.patchValue({
        cep: cep,
        state: state,
        city: city,
        neighborhood: neighborhood,
        address: street,
        country: 'Brasil'
      });
    }
  }

  verifyResponse(data: any) {
    if (data.status == 201) {
      if (this.studentsData.financialResponsible == 'TRUE') {
        this.createFinancialResponsible(data.body.id);    
      } else {
        alert("Dados Cadastrados!");
        this.router.navigate(["students/register/stage2/" + data.body.id]);
      }
    } else if (data.status == 401) {
      alert("Sua sessão expirou! Faça login novamente.");
      this.resetSessionUser();
    }
  }

  resetSessionUser(): void {
    this.router.navigate([""]);
  }

  createFinancialResponsible(data: any) {
    this.prepareFinancialResponsibleData(data);
    this.studentService.createFinancialResponsible(this.formDataFinancialResponsible).subscribe({
      next: (response: HttpResponse<any>) => {
        alert("Dados Cadastrados!");
        this.router.navigate(["students/register/stage2/" + data]);
      },
      error: (error) => console.log("Financial Responsible error", error.message)
    })
  }

  prepareFinancialResponsibleData(studentId: any): void {
    this.formDataFinancialResponsible.append('name', this.studentsData.name);
    this.formDataFinancialResponsible.append('email', this.studentsData.email);
    this.formDataFinancialResponsible.append('dateOfBirth', this.studentsData.dateOfBirth);
    this.formDataFinancialResponsible.append('address', this.studentsData.address);
    this.formDataFinancialResponsible.append('workAddress', this.studentsData.address);
    this.formDataFinancialResponsible.append('occupation', 'STUDENT');
    this.formDataFinancialResponsible.append('neighborhood', this.studentsData.neighborhood);
    this.formDataFinancialResponsible.append('numberHouse', this.studentsData.numberHouse);
    this.formDataFinancialResponsible.append('city', this.studentsData.city);
    this.formDataFinancialResponsible.append('phone', this.studentsData.phone);
    this.formDataFinancialResponsible.append('state', this.studentsData.state);
    this.formDataFinancialResponsible.append('cep', this.studentsData.cep);
    this.formDataFinancialResponsible.append('type', 'STUDENT');
    this.formDataFinancialResponsible.append('studentId', studentId);
    this.formDataFinancialResponsible.append('cpf', this.studentsData.cpf);
    this.formDataFinancialResponsible.append('identity', this.studentsData.identity);
    this.formDataFinancialResponsible.append('nationality', this.studentsData.nationality);
    this.formDataFinancialResponsible.append('naturalness', this.studentsData.naturalness);
    this.formDataFinancialResponsible.append('country', this.studentsData.country);
  }
}
