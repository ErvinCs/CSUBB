import {Person, Gender, Role} from '../../models/common.interface';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  person: Person = undefined;
  private role: Role.none;
  // TODO: Make some validation for the input

  constructor() {
  }

  ngOnInit() {
    console.log(this.person)
  }
}
