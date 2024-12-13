import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Location } from '@angular/common'

import { HeroService } from "../hero.service";
import { Hero } from "../hero";

@Component({
  selector: 'app-hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: ['./hero-detail.component.css']
})
export class HeroDetailComponent implements OnInit {
  hero: Hero;

  getHero(): void {
    //
    const id = +this.route.snapshot.paramMap.get('id');
    this.heroService.getHero(id)
      .subscribe(hero => this.hero = hero)
  }

  constructor(
    private route: ActivatedRoute,  //Information about the rout of this instance - helps with URL parameters
    private heroService: HeroService, //Gets hero data from the remote server
    private location: Location  //Interact with the browser
  ) { }

  ngOnInit() {
    this.getHero();
  }

  goBack(): void {
    this.location.back();
  }
}
