import { Component, OnInit } from '@angular/core';
import {Doctor} from '../../models/common.interface';
import {DoctorService} from '../../services/doctor.service';

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent implements OnInit {

  doctors: Doctor[];

  constructor(private doctorService: DoctorService) { }

  // getDoctors(): void {
  //   this.doctorService.getDoctors().subscribe(doctors =>
  //     this.doctors = doctors);
  // }
  //
  // add(name: string): void {
  //   name = name.trim();
  //   if (!name) { return; }
  //   this.doctorService.addDoctor({ name } as Doctor)
  //     .subscribe(doctor => {
  //       this.doctors.push(doctor);
  //     });
  // }
  //
  // delete(doctor: Doctor): void {
  //   this.doctors = this.doctors.filter(d => d !== doctor);
  //   this.doctorService.deleteHero(doctor).subscribe();
  // }

  ngOnInit() {
    // We need this such that we can get the data from the begging, so that we can get the
    // values from the starting point.
    // this.getDoctors();
  }
}
