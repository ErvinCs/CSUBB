import { Injectable } from '@angular/core';
import {Donor} from '../models/common.interface';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs/internal/Observable";

@Injectable()
export class DonorService {
  private apiUrl = '/something??';
  private httpHeaders: HttpHeaders;

  constructor(private httpClient: HttpClient) { }

  getDonors(): Observable<Donor[]> {
    return null;
  }

  // This is for the login part
  getDonor(): Observable<Donor> {
    return null;
  }

  // This is for the registration.
  addDonor(donor: Donor): void {
  }

  deleteHero(donor: Donor): void {
  }
}
