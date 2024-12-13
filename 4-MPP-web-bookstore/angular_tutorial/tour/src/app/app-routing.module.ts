import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";

import { HeroesComponent } from "./heroes/heroes.component";
import { HeroDetailComponent } from "./hero-detail/hero-detail.component";
import { DashboardComponent } from "./dashboard/dashboard.component";

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  { path: 'dashboard', component: DashboardComponent },
  { path: 'heroes', component: HeroesComponent },
  //Parameterized - :id - a placeholder for a specific hero id
  { path: 'detail/:id', component: HeroDetailComponent }
];

//ng generate module app-routing --flat --module=app
// - flat puts the file in src/app instead of a new folder of its own
// - module=app tells the CLI to register it in the imports array of AppModule
@NgModule({
  // Supply the service providers and directives needed for routing
  imports: [ RouterModule.forRoot(routes) ],
  //Exporting RouterModule makes router directives available for use in AppModule
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
