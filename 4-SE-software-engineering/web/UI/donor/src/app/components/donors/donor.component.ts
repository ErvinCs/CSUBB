import { Component, OnInit } from '@angular/core';
import {Donor, BloodType, Gender, Person, Birthday} from "../../models/common.interface";

@Component({
    selector: 'app-donors',
    templateUrl: './donor.component.html',
    styleUrls: ['./donor.component.css']
})
export class DonorComponent implements OnInit {

    public donor: Donor;

    // constructor(private donorService: DonorService) { }
    constructor() {
    }

    ngOnInit() {
        // TODO: make this as an api call, to get it from the session
        // this.donor = getUser();
    }
}

function getUser() {
    let person: Person = {
        firstName: "Denis",
        lastName: "Fovas",
        birthday: {
            day: 9,
            month: 10,
            year: 1997
        },
        residence: {
            city: "Cluj",
            country: "RO",
            street: "some random street."
        },
        address: {
            city: "Cluj",
            country: "RO",
            street: "some random street."
        },
        username: "denis",
        password: "denis",
        cnp: 123456789123,
        email: "denis@iss.com",
        phoneNumber: 123456789,
        gender: Gender.male,
        bloodType: BloodType.A2n,
    };
    let currentD: Birthday = {
        day: 12,
        month: 12,
        year: 2000
    };
    return {
        person: person,
        currentAppointment: currentD,
        currentDonation: currentD,
        lastDonation: currentD,
        donationHistory: [currentD]
    }
}
