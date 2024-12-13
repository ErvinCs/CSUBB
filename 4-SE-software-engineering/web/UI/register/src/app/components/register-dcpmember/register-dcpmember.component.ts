import { Person } from '../../models/common.interface';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register-dcpmember',
  templateUrl: './register-dcpmember.component.html',
  styleUrls: ['./register-dcpmember.component.css']
})
export class RegisterDcpmemberComponent implements OnInit {
  private person: Person

  constructor() { }


  ngOnInit() {
  }

  createPerson() {

  }
}
