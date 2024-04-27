import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FamilyData } from 'src/app/domain/student/FamilyData';
import { FinancialResponsibleData } from 'src/app/domain/student/FinancialResponsibleData';
import { CepService } from 'src/app/services/externalApi/cep-service.service';
import { FormStudentsDataService } from 'src/app/services/form-students-data.service';
import { CofirmOutsideClick } from 'src/app/utils/ConfirmOutsideClick';

@Component({
  selector: 'app-students-register-stage2',
  templateUrl: './students-register-stage2.component.html',
  styleUrls: ['./students-register-stage2.component.scss']
})
export class StudentsRegisterStage2Component implements OnInit{
  idStudent: string = '';
  addFamilyButton: boolean = false;
  saveFamilyButton: boolean = true;
  nextStageAvaliable: boolean = false;
  showFinancialresponsibleOptions: boolean = true;

  financialResponsibleData = new FinancialResponsibleData();
  familyData = new FamilyData();
  formData = new FormData();
  formDataFinancialResponsible = new FormData();

  rg: boolean = false;
  cpf: boolean = false;
  proofOfAddress: boolean = false;

  form: FormGroup;

  constructor(
    private formStudentService: FormStudentsDataService, 
    private router: Router,
    private studentService: FormStudentsDataService,
    private cepService: CepService,
    private route: ActivatedRoute,
    private confirm: CofirmOutsideClick,
    private fb: FormBuilder) {
      this.form = this.fb.group({
        name: ['', Validators.required],
        cpf: ['', Validators.required],
        identity: ['', Validators.required],
        email: ['', Validators.required],
        dateOfBirth: ['', Validators.required],
        occupation: ['', Validators.required],
        type: ['', Validators.required],
        // imageProfile: ['', Validators.required],
        // rgFile: ['', Validators.],
        // cpfFile: ['', Validators.required],
        // proofOfAddress: ['', Validators.required],
        cep: ['', Validators.required],
        address: ['', Validators.required],
        numberHouse: ['', Validators.required],
        neighborhood: ['', Validators.required],
        nationality: ['', Validators.required],
        city: ['', Validators.required],
        workAddress: ['', Validators.required],
        phone: ['', Validators.required],
        state: ['', Validators.required],
        country: ['', Validators.required],
        naturalness: ['', Validators.required],
        financialResponsible: ['', Validators.required]
    });
  }
   
  ngOnInit(): void {
    const param = this.route.snapshot.paramMap.get('id');
    this.idStudent = param !== null ? param : '';
    this.familyData.studentId = param ?? '';
    this.verifyIfFinancialResponsibleIsPresentForShowOptions();

    this.form.valueChanges.subscribe((value) => {
      this.familyData = {...value};
    })
  }

  handleOutsideClick(event: Event): void {
    this.confirm.handleOutsideClick(event, 'student', this.idStudent);
  }

  verifyIfFinancialResponsibleIsPresentForShowOptions() {
    this.studentService.findStudentById(this.idStudent).subscribe({
      next: (response: any) => {
        if (response.body.financialResponsible == null) {
          this.showFinancialresponsibleOptions = true;
        } else {
          this.showFinancialresponsibleOptions = false;
          this.form.patchValue({
            financialResponsible: 'FALSE'
          })
        }
      },
      error: (error) => console.log("Error to search financial responsible by student")
    });
  }

  saveFamily() {
    this.setStudentDataInFormData();

    if (this.form.valid) {
      this.formStudentService.createFamily(this.formData).subscribe({
        next: (response: HttpResponse<any>) => {
          if (response.status == 201) {
            
            if (this.familyData.financialResponsible == 'TRUE') {
              this.createFinancialResponsible(this.idStudent);
            } else {
              alert("Familiar salvo com sucesso!");
            }
            
            this.nextStepButtonAfterSavingAFamilyMember();
          }
        },
        error: (error) => alert('Erro ao registrar familiar, verifique as informações!')
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



  nextStepButtonAfterSavingAFamilyMember() {
    this.studentService.findStudentById(this.idStudent).subscribe({
      next: (response: any) => {
        console.log(response.body.family.length <= 2)
        if (response.body.family.length <= 1) {
          this.addFamilyButton = true;
          this.saveFamilyButton = false;  
          this.nextStageAvaliable = true;
        } else {
          this.addFamilyButton = false;
          this.saveFamilyButton = false;  
          this.nextStageAvaliable = true;
        }
      }
    });

  

  }

  setStudentDataInFormData() {
    this.formData.append('studentId', this.idStudent);
    Object.keys(this.familyData).forEach(key => {
      const safeKey = key as keyof FamilyData;
      if (this.familyData[safeKey] != null) {
        this.formData.set(safeKey, this.familyData[safeKey]);
      }
    })
  }

  
  resetFormFromAddNewFamily() {
    debugger
    this.familyData = {
      studentId: this.idStudent,
      name: '',
      address: '',
      workAddress: '',
      occupation: '',
      neighborhood: '',
      numberHouse: '',
      type: '',
      city: '',
      phone: '',
      email: '',
      state: '',
      cep: '',
      dateOfBirth: '',
      cpf: '',
      identity: '',
      financialResponsible: '',
      naturalness: '',
      nationality: '',
      country: ''
    };

    this.verifyIfFinancialResponsibleIsPresentForShowOptions();
    this.resetFromInitialButtonOrder();
    this.rg = false;
    this.cpf = false;
    this.proofOfAddress = false;

    this.form.patchValue({
      financialResponsible: 'FALSE',
      name: '',
      address: '',
      workAddress: '',
      occupation: '',
      neighborhood: '',
      numberHouse: '',
      type: '',
      city: '',
      phone: '',
      email: '',
      state: '',
      cep: '',
      dateOfBirth: '',
      cpf: '',
      identity: '',
      naturalness: '',
      nationality: '',
      country: ''
    })
  }

  resetFromInitialButtonOrder() {
    this.addFamilyButton = false;
    this.saveFamilyButton = true;
    this.nextStageAvaliable = false;
  }

  nextStage() {
    if (this.showFinancialresponsibleOptions) {
      this.router.navigate(["students/register/financialresponsible/" + this.idStudent]);
    } else {
      this.router.navigate(["students/register/stage3/" + this.idStudent]);
    }
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

  createFinancialResponsible(data: any) {
    this.prepareFinancialResponsibleData(data);
    this.studentService.createFinancialResponsible(this.formDataFinancialResponsible).subscribe({
      next: (response: HttpResponse<any>) => {
        alert("Familiar salvo com sucesso!");
        this.showFinancialresponsibleOptions = false;
        this.router.navigate(["students/register/stage2/" + data]);
      },
      error: (error) => console.log("Financial Responsible error", error.message)
    })
  }

  prepareFinancialResponsibleData(studentId: any): void {
    this.formDataFinancialResponsible.append('name', this.familyData.name);
    this.formDataFinancialResponsible.append('email', this.familyData.email);
    this.formDataFinancialResponsible.append('dateOfBirth', this.familyData.dateOfBirth);
    this.formDataFinancialResponsible.append('address', this.familyData.address);
    this.formDataFinancialResponsible.append('workAddress', this.familyData.address);
    this.formDataFinancialResponsible.append('occupation', 'STUDENT');
    this.formDataFinancialResponsible.append('neighborhood', this.familyData.neighborhood);
    this.formDataFinancialResponsible.append('numberHouse', this.familyData.numberHouse);
    this.formDataFinancialResponsible.append('city', this.familyData.city);
    this.formDataFinancialResponsible.append('phone', this.familyData.phone);
    this.formDataFinancialResponsible.append('state', this.familyData.state);
    this.formDataFinancialResponsible.append('cep', this.familyData.cep);
    this.formDataFinancialResponsible.append('type', 'STUDENT');
    this.formDataFinancialResponsible.append('studentId', studentId);
    this.formDataFinancialResponsible.append('cpf', this.familyData.cpf);
    this.formDataFinancialResponsible.append('identity', this.familyData.identity);
    this.formDataFinancialResponsible.append('nationality', this.familyData.nationality);
    this.formDataFinancialResponsible.append('naturalness', this.familyData.naturalness);
    this.formDataFinancialResponsible.append('country', this.familyData.country);
  }
  
}
