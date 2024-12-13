import { Injectable } from '@angular/core';
import { Observable, of} from "rxjs";

import { MessageService } from "./message.service";
import { Hero } from "./hero";
import { HEROES } from "./mock-heroes";

// Marks the class as one that participates in dependency injection
// @Injectable accepts a metadata object for the service
// Make the service available to dependency injection -
//  register a provider (can create or deliver a service) that instantiates HeroService
@Injectable({
  providedIn: 'root'
})
export class HeroService {

  getHero(id: number): Observable<Hero> {
    this.messageService.add(`HeroService: fetched hero id=${id}`);  //Used backticks (`) to embed the id
    return of(HEROES.find(hero => hero.id === id))
  }

  getHeroes(): Observable<Hero[]> {
    //Send a message at fetch
    this.messageService.add('HeroService: fetched heroes');
    return of(HEROES);
  }

  // Angular will inject MessageService into the property
  constructor(private messageService: MessageService) { }
}
