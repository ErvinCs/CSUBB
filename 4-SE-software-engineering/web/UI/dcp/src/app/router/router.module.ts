import { Routes } from "@angular/router";
import {DcpMemberComponent} from "../components/dcp-member/dcp-member.component";
import {BloodStocksComponent} from "../components/blood-stocks/blood-stocks.component";
import {IdleDonorsComponent} from "../components/idle-donors/idle-donors.component";
import {BloodRequestListComponent} from "../components/blood-request-list/blood-request-list.component";
import {DonorDataComponent} from "../components/donor-data/donor-data.component";

export const routes: Routes = [
  {
    path: "",
    component: DcpMemberComponent
  },
  {
    path: "bloodStocks",
    component: BloodStocksComponent
  },
  {
    path: "idleDonors",
    component: IdleDonorsComponent
  },
  {
    path: "bloodRequests",
    component: BloodRequestListComponent
  },
  {
    path: "donorData",
    component: DonorDataComponent
  }
];
