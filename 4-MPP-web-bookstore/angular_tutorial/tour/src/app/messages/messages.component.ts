import { Component, OnInit } from '@angular/core';

import { MessageService } from "../message.service";

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  // Angular will inject the MessageService singleton
  // Public because it will be bound in the template (only public properties can be bound)
  constructor(public messageService: MessageService) { }

  ngOnInit() {
  }

}
