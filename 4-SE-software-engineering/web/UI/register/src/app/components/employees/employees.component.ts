import { Component, OnInit } from '@angular/core';
import {DCPEmployee} from '../../models/common.interface';
import {DcpMemberService} from '../../services/dcp-member.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  employees: DCPEmployee[];

  constructor(private employeeService: DcpMemberService) { }
  //
  // getEmployees(): void {
  //   this.employeeService.getEmployees().subscribe(employees =>
  //     this.employees = employees);
  // }
  //
  // add(name: string): void {
  //   name = name.trim();
  //   if (!name) { return; }
  //   this.employeeService.addEmployee({ name } as DCPEmployee)
  //     .subscribe(employee => {
  //       this.employees.push(employee);
  //     });
  // }
  //
  // delete(employee: DCPEmployee): void {
  //   this.employees = this.employees.filter(e => e !== employee);
  //   this.employeeService.deleteHero(employee).subscribe();
  // }
  //
  ngOnInit() {
    // this.getEmployees();
  }

}
