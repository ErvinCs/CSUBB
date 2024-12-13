export interface Person {
    firstName: string,
    lastName: string,
    birthday: Birthday,
    residence: Address,
    address: Address
    username: string
    password: string
    cnp: number
    email: string
    phoneNumber: number
    gender: string
}

export interface Birthday {
    day: number,
    month: number,
    year: number
}

export interface Address {
    city: string,
    country: string,
    street: string
}

export enum Gender {
    male = 'Male',
    female = 'Female',
    na = ''
}

export interface Donor {
    person: Person,
    currentDonation: Date,
}
