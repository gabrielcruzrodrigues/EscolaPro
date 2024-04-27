import { Shift } from "./ShiftsEnum";

export class StudentData {
    name: string =  '';
    identity: string = '';
    cpf: string = '';
    dateOfBirth: string = '';
    nationality: string = '';
    naturalness: string = '';
    sex: string = '';
    email: string = '';
    cep: string = '';
    address: string = '';
    phone: string = '';
    neighborhood: string = '';
    city: string = '';
    state: string = '';
    country: string =  'Brasil';
    emailPersonResponsible: string = '';
    responsible: string = '';
    father: string = '';
    mother: string = '';
    shifts: Shift = Shift.Matutino;
    situation: string = '';
    numberHouse: string = '';
    financialResponsible: string = '';
}
