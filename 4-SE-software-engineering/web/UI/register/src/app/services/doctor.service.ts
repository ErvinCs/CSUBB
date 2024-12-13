import { Injectable } from '@angular/core';
import {Doctor} from '../models/common.interface';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class DoctorService {
  private baseUrl = '/api/doctor';
  private httpOptions  = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) { }

  getDoctors() {
    return this.http.get<Doctor[]>(`${this.baseUrl}s`)
  }

  addDoctor(doctor: Doctor): any {
    // return this.http.post(`${this.baseUrl}`, doctor, this.httpOptions);
  }

  deleteHero(doctor: Doctor) {
    // TODO: Change this, make it so that can call the API for fuck sake.
    // return this.http.delete(`${this.baseUrl}/${doctor.person.cnp}`, this.httpOptions)
  }
}
