import {Routes} from "@angular/router";
import {DoctorComponent} from "../components/doctor/doctor.component";
import {BloodRequestComponent} from "../components/blood-request/blood-request.component";
import {CheckRequestStatusComponent} from "../components/check-request-status/check-request-status.component";

export const routes: Routes = [
  {
    path: "",
    component: DoctorComponent
  },
  {
    path: "bloodRequest",
    component: BloodRequestComponent
  },
  {
    path: "requestStatus",
    component: CheckRequestStatusComponent
  }
];
