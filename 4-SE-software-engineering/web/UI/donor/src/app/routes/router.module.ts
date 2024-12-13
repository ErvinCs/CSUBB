import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DonorComponent} from "../components/donors/donor.component";
import {DonationHistoryComponent} from "../components/donation-history/donation-history.component";
import {NextDonationComponent} from "../components/next-donation/next-donation.component";
import {CurrentAppointmentComponent} from "../components/current-appointment/current-appointment.component";
import {DonateComponent} from "../components/donate/donate.component";

export const routes: Routes = [
    {
        path: "",
        component: DonorComponent
    },
    {
        path: "history",
        component: DonationHistoryComponent
    },
    {
        path: "nextAppointment",
        component: NextDonationComponent
    },
    {
        path: "currentAppointment",
        component: CurrentAppointmentComponent
    },
    {
        path: "donate",
        component: DonateComponent
    },
    {
        path: "logout",
        //TODO:change this
        component: DonorComponent
    }
];