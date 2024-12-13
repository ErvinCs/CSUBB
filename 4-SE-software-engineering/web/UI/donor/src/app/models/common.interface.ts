export interface Person {
  firstName: string
  lastName: string
  birthday: Birthday
  residence: Address
  address: Address
  username: string
  password: string
  cnp: number
  email: string
  phoneNumber: number
  gender: string
  bloodType: BloodType
}
export interface DonationCenter {
  address: Address
  name: string
}
export enum BloodType {
  unknown='',
  O1P='O1 + ',
  A2p='A2 + ',
  B3p='B3 + ',
  AB4p='AB4 + ',
  O1n='O1 - ',
  A2n='A2 - ',
  B3n='B3 - ',
  AB4n='AB4 - '
}
export enum Severity {
  low='Low',
  medium='Medium',
  urgent='Urgent'
}
export interface Birthday {
  day: number
  month: number
  year: number
}

export interface Address {
  city: string
  country: string
  street: string
}

export enum Gender {
  male = 'Male',
  female = 'Female',
  na = 'Don\'t say'
}

export interface Donor {
  person: Person;
  currentAppointment: Date;
  currentDonation: Birthday;
  lastDonation: Birthday;
  donationHistory: Birthday[];
}
export enum Role {
  donor='donor',
  doctor='doctor',
  dcp='dcp',
  none=''
}
export interface Doctor {
  person: Person;
  hospital: string;
  patients: Person[];
  makeRequest(patient: Person, priority: Severity, bloodType: BloodType);
}

export interface DCPEmployee {
  person: Person
  donatingCenter: DonationCenter
}

export interface DonationAppoinment {
  donationCenter: DonationCenter,
  date: Date
}

export interface BloodRequest {
  requester: Doctor,
  providers: DonationCenter[],
  patient: Person,
  bloodType: BloodType,
  bloodNeeded: number,
  bloodGiven: number
}
