import { Injectable } from '@angular/core';
import {Donor} from '../models/common.interface';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class DonorService {
  // TODO: make the api calls, for every fucking thing...
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
