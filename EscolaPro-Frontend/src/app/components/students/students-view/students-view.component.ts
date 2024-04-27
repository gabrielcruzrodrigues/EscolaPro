import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FormStudentsDataService } from 'src/app/services/form-students-data.service';

@Component({
  selector: 'app-students-view',
  templateUrl: './students-view.component.html',
  styleUrls: ['./students-view.component.scss']
})
export class StudentsViewComponent implements OnInit{
  studentId: string = '';
  image: boolean = false;
  rg: boolean = false;
  cpf: boolean = false;
  proofOfAddress: boolean = false;

  form: FormGroup;
  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private studentService: FormStudentsDataService,
    private router: Router) {
    this.form = this.fb.group({
      name: [''],
      cpf: [''],
      identity: [''],
      dateOfBirth: [''],
      phone: [''],
      email: [''],
      cep: [''],
      address: [''],
      city: [''],
      neighborhood: [''],
      numberHouse: [''],
      nationality: [''],
      sex: [''],
      state: [''],
      country: [''],
      // imageProfile: [''],
      // rgFile: ['', Validators.],
      // cpfFile: [''],
      // proofOfAddress: [''],
      responsible: [''],
      emailPersonResponsible: [''],
      father: [''],
      mother: [''],
      naturalness: [''],
      financialResponsible: [''],

      // family 1

      familyName: [''],
      familyCpf: [''],
      familyIdentity: [''],
      familyEmail: [''],
      familyDateOfBirth: [''],
      familyOccupation: [''],
      familyType: [''],
      // imageProfile: [''],
      // rgFile: ['', Validators.],
      // cpfFile: [''],
      // proofOfAddress: [''],
      familyCep: [''],
      familyAddress: [''],
      familyNumberHouse: [''],
      familyNeighborhood: [''],
      familyNationality: [''],
      familyCity: [''],
      familyWorkAddress: [''],
      familyPhone: [''],
      familyState: [''],
      familyCountry: [''],
      familyNaturalness: [''],
      familyFinancialResponsible: [''],

      // family 2

      family2Name: [''],
      family2Cpf: [''],
      family2Identity: [''],
      family2Email: [''],
      family2DateOfBirth: [''],
      family2Occupation: [''],
      family2Type: [''],
      // imageProfile: [''],
      // rgFile: ['', Validators.],
      // cpfFile: [''],
      // proofOfAddress: [''],
      family2Cep: [''],
      family2Address: [''],
      family2NumberHouse: [''],
      family2Neighborhood: [''],
      family2Nationality: [''],
      family2City: [''],
      family2WorkAddress: [''],
      family2Phone: [''],
      family2State: [''],
      family2Country: [''],
      family2Naturalness: [''],
      family2FinancialResponsible: [''],

      // financial responsible

      financialName: [''],
      financialCpf: [''],
      financialIdentity: [''],
      financialOccupation: [''],
      financialNationality: [''],
      financialDateOfBirth: [''],
      // imageProfile: [''],
      // rgFile: ['', Validators.],
      // cpfFile: [''],
      // proofOfAddress: [''],
      financialCep: [''],
      financialAddress: [''],
      financialWorkAddress: [''],
      financialNeighborhood: [''],
      financialNumberHouse: [''],
      financialCity: [''],
      financialState: [''],
      financialCountry: [''],
      financialPhone: [''],
      financialEmail: [''],
      financialNaturalness: [''],
      financialType: [''],

      // fixed health

      allergiesTextArea: [],
      healthInsurance: [''],
      bloodGroup: [''],
      medicalClinic: [''],
      quantityBrothers: [''],
      toGoOutAuthorization: ['']
    });
  }

  ngOnInit(): void {
    const param = this.route.snapshot.paramMap.get('id');
    this.studentId = param !== null ? param : '';

    this.studentService.findStudentById(this.studentId).subscribe({
      next: (response: HttpResponse<any>) => this.populateForm(response),
      error: (error) => console.log(error)
    })
  }

  sendData(option: string) {
    if (option == 'update') {
      this.router.navigate(["students/update/" + this.studentId]);
    } else if (option == 'disabled') {
      this.router.navigate(["students/disabled/" + this.studentId]);
    } else {
      console.log("Never option value.");
    }
  }

  downloadFile(type: string):void {
    if (type) {
      this.studentService.DownloadFile(this.studentId, type).subscribe(response => {
        const blob = response.body;
        const contentDisposition = response.headers.get('Content-Disposition');
        console.log(response);
        let filename = 'arquivo.pdf';        
        
        if (contentDisposition) {
          const matches = /filename="([^"]*)"/.exec(contentDisposition);
          if (matches && matches[1]) {
            filename = matches[1];
          }
        }
        
        if (blob) {
          const url = window.URL.createObjectURL(blob);
          const link = document.createElement('a');
          link.href = url;
          link.download = filename;
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
          window.URL.revokeObjectURL(url);
        } else {
          console.log("Falha ao receber o arquivo do servidor.");
        }
      });
    }
  }

  populateForm(response: any): void {
    console.log(response)
    this.form.patchValue({
      name: response.body.name,
      cpf: response.body.cpf,
      identity: response.body.identity,
      dateOfBirth: response.body.dateOfBirth,
      phone: response.body.phone,
      email: response.body.email,
      cep: response.body.cep,
      address: response.body.address,
      city: response.body.city,
      neighborhood: response.body.neighborhood,
      numberHouse: response.body.numberHouse,
      nationality: response.body.nationality,
      sex: response.body.sex,
      state: response.body.state,
      country: response.body.country,
      // imageProfile: response.body.name,
      // rgFile: ['', Validators.],
      // cpfFile: response.body.name,
      // proofOfAddress: response.body.name,
      responsible: response.body.responsible,
      emailPersonResponsible: response.body.emailPersonResponsible,
      father: response.body.father,
      mother: response.body.mother,
      naturalness: response.body.naturalness,

      // financial responsible

      financialName: response.body.financialResponsible.name,
      financialCpf: response.body.financialResponsible.cpf,
      financialIdentity: response.body.financialResponsible.identity,
      financialOccupation: response.body.financialResponsible.occupation,
      financialNationality: response.body.financialResponsible.nationality,
      financialDateOfBirth: response.body.financialResponsible.dateOfBirth,
      // imageProfile: response.body.financialResponsible.name,
      // rgFile: [''],
      // cpfFile: response.body.financialResponsible.name,
      // proofOfAddress: response.body.financialResponsible.name,
      financialCep: response.body.financialResponsible.cep,
      financialAddress: response.body.financialResponsible.address,
      financialWorkAddress: response.body.financialResponsible.workAddress,
      financialNeighborhood: response.body.financialResponsible.neighborhood,
      financialNumberHouse: response.body.financialResponsible.numberHouse,
      financialCity: response.body.financialResponsible.city,
      financialState: response.body.financialResponsible.state,
      financialCountry: response.body.financialResponsible.country,
      financialPhone: response.body.financialResponsible.phone,
      financialEmail: response.body.financialResponsible.email,
      financialNaturalness: response.body.financialResponsible.naturalness,
      financialType: response.body.financialResponsible.type,

      // fixed health

      
    });

    if (response.body.fixedHealth) {
      this.form.patchValue({
        allergiesTextArea: response.body.fixedHealth.allergies,
        healthInsurance: response.body.fixedHealth.healthInsurance,
        bloodGroup: response.body.fixedHealth.bloodGroup,
        medicalClinic: response.body.fixedHealth.medicalClinic,
        quantityBrothers: response.body.fixedHealth.quantityBrothers,
        toGoOutAuthorization: response.body.fixedHealth.toGoOutAuthorization
      })
    }

    //family 1
    if (response.body.family[0]) {
      this.form.patchValue({
        familyName: response.body.family[0].name,
        familyCpf: response.body.family[0].cpf,
        familyIdentity: response.body.family[0].identity,
        familyEmail: response.body.family[0].email,
        familyDateOfBirth: response.body.family[0].dateOfBirth,
        familyOccupation: response.body.family[0].occupation,
        familyType: response.body.family[0].type,
        // imageProfile: response.body.family[0].naturalness,
        // rgFile: ['', Validators.],
        // cpfFile: response.body.family[0].naturalness,
        // proofOfAddress: response.body.family[0].naturalness,
        familyCep: response.body.family[0].cep,
        familyAddress: response.body.family[0].address,
        familyNumberHouse: response.body.family[0].numberHouse,
        familyNeighborhood: response.body.family[0].neighborhood,
        familyNationality: response.body.family[0].nationality,
        familyCity: response.body.family[0].city,
        familyWorkAddress: response.body.family[0].workAddress,
        familyPhone: response.body.family[0].phone,
        familyState: response.body.family[0].state,
        familyCountry: response.body.family[0].country,
        familyNaturalness: response.body.family[0].naturalness,
      })
    }

    // family 2
    if (response.body.family[1]) {
      this.form.patchValue({
    
        family2Name: response.body.family[1].name,
        family2Cpf: response.body.family[1].cpf,
        family2Identity: response.body.family[1].identity,
        family2Email: response.body.family[1].email,
        family2DateOfBirth: response.body.family[1].dateOfBirth,
        family2Occupation: response.body.family[1].occupation,
        family2Type: response.body.family[1].type,
        // imageProfile: response.body.family[1].naturalness,
        // rgFile: ['', Validators.],
        // cpfFile: response.body.family[1].naturalness,
        // proofOfAddress: response.body.family[1].naturalness,
        family2Cep: response.body.family[1].cep,
        family2Address: response.body.family[1].address,
        family2NumberHouse: response.body.family[1].numberHouse,
        family2Neighborhood: response.body.family[1].neighborhood,
        family2Nationality: response.body.family[1].nationality,
        family2City: response.body.family[1].city,
        family2WorkAddress: response.body.family[1].workAddress,
        family2Phone: response.body.family[1].phone,
        family2State: response.body.family[1].state,
        family2Country: response.body.family[1].country,
        family2Naturalness: response.body.family[1].naturalness,
      });
    }
  }
}

