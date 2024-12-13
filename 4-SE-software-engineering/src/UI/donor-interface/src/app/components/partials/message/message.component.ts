import { Component, OnInit } from '@angular/core';
import {AlertServiceService} from '../../../servicess/alert-service.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {
  private message: string;
  constructor(private alertService: AlertServiceService) { }

  ngOnInit() {
    this.alertService.getMessage().subscribe(message => this.message = message);
  }

}
