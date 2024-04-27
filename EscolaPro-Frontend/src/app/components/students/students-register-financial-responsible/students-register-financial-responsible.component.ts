import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FinancialResponsibleData } from 'src/app/domain/student/FinancialResponsibleData';
import { CepService } from 'src/app/services/externalApi/cep-service.service';
import { FormStudentsDataService } from 'src/app/services/form-students-data.service';
import { CofirmOutsideClick } from 'src/app/utils/ConfirmOutsideClick';

@Component({
  selector: 'app-students-register-financial-responsible',
  templateUrl: './students-register-financial-responsible.component.html',
  styleUrls: ['./students-register-financial-responsible.component.scss']
})
export class StudentsRegisterFinancialResponsibleComponent implements OnInit{
  idStudent: string = '';
  formData = new FormData();
  formDataFinancialResponsible = new FinancialResponsibleData();

  rg: boolean = false;
  cpf: boolean = false;
  proofOfAddress: boolean = false;

  form: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private cepService: CepService, 
    private studentService: FormStudentsDataService,
    private confirm: CofirmOutsideClick,
    private router: Router,
    private fb: FormBuilder) {
      this.form = this.fb.group({
        name: ['', Validators.required],
        cpf: ['', Validators.required],
        identity: ['', Validators.required],
        occupation: ['', Validators.required],
        nationality: ['', Validators.required],
        dateOfBirth: ['', Validators.required],
        // imageProfile: ['', Validators.required],
        // rgFile: ['', Validators.],
        // cpfFile: ['', Validators.required],
        // proofOfAddress: ['', Validators.required],
        cep: ['', Validators.required],
        address: ['', Validators.required],
        workAddress: ['', Validators.required],
        neighborhood: ['', Validators.required],
        numberHouse: ['', Validators.required],
        city: ['', Validators.required],
        state: ['', Validators.required],
        country: ['', Validators.required],
        phone: ['', Validators.required],
        email: ['', Validators.required],
        naturalness: ['', Validators.required],
        type: ['', Validators.required]
      });
    }

  ngOnInit(): void {
    const param = this.route.snapshot.paramMap.get('id');
    this.idStudent = param !== null ? param : '';
    this.formData.append('studentId', this.idStudent);

    this.form.valueChanges.subscribe((value) => {
      this.formDataFinancialResponsible = {...value};
    })
  }

  handleOutsideClick(event: Event): void {
    this.confirm.handleOutsideClick(event, 'student', this.idStudent);
  }

  sendData() {
    this.setFinancialResponsibleInFormData();

    if (this.form.valid) {
      this.studentService.createFinancialResponsible(this.formData).subscribe({
        next: (response: HttpResponse<any>) => {
          this.verifyResponse(response);
        },
        error: (err) => console.log('Erro ao deixar estudante como pendente', err)
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

  setFinancialResponsibleInFormData() {
    Object.keys(this.formDataFinancialResponsible).forEach(key => {
      const safeKey = key as keyof FinancialResponsibleData;
      if (this.formDataFinancialResponsible[safeKey] != null) {
        console.log(safeKey);
        this.formData.set(safeKey, this.formDataFinancialResponsible[safeKey]);
      }
    })
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
      alert("Dados Cadastrados!");
      this.router.navigate(["students/register/stage3/" + this.idStudent]);
    } else {
      alert("Erro ao tentar enviar formulário.");
    }
  }

  onRgSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.formData.append('rgFile', file, file.name);
      this.rg = true;
    }
  }

  onCpfSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.formData.append('cpfFile', file, file.name);
      this.cpf = true;
    }
  }

  onProofOfAddressSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.formData.append('proofOfAddress', file, file.name);
      this.proofOfAddress = true;
    }
  }

}
