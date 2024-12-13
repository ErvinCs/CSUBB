import { Component, OnInit } from '@angular/core';

import { Hero } from "../hero";
import { HeroService } from "../hero.service";

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {
  heroes: Hero[];

  // Must be asynchronous - either take a callback, return a promise, return an Observable
  // HttpClient.get() - returns an Observable (RxJS)
  // Observable emits the array of heroes, subscribe passes the array to the callback which sets the heroes property
  getHeroes(): void {
    this.heroService.getHeroes()
      .subscribe(heroes => this.heroes = heroes);
  }

  // When Angular creates a HeroesComponent, the Dependency Injection system sets
  //  the heroService parameter to the singleton instance of HeroService.
  constructor(private heroService: HeroService) { }

  ngOnInit() {
    this.getHeroes();
  }
}
