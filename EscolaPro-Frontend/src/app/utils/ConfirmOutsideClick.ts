import { HttpResponse } from "@angular/common/http";
import { FormStudentsDataService } from "../services/form-students-data.service";

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class CofirmOutsideClick {

    constructor(private studentService: FormStudentsDataService) {}

    handleOutsideClick(event: Event, domain: string, id: string): void {
        if (confirm('Deseja cancelar o cadastro do aluno?')) {
            if (domain == 'student') {
                this.cancelRegistrationStudent(id);
            };
        } else {
            event.preventDefault();
            alert("Você escolheu continuar com o cadastro.");
        }
    }

    cancelRegistrationStudent(id: string): void {
        this.studentService.deleteStudent(id).subscribe({
            next: (response: HttpResponse<any>) => {
                if (response.status == 204) {
                    alert("Cadastro cancelado.");
                }
            }, 
            error: (error) => console.log(`Erro ao deletar o estudante. Erro: ${error}`)
        })
    }

    handleOutsideClickFirstStage(event: Event): void {
        if (confirm('Deseja cancelar o cadastro do aluno?')) {
            alert("cadastro cancelado!");
        } else {
            event.preventDefault();
            alert("Você escolheu continuar com o cadastro.");
        }
    }
}