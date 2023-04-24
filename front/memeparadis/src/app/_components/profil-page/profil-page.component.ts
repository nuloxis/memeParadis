import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profil-page',
  templateUrl: './profil-page.component.html',
  styleUrls: ['./profil-page.component.css']
})
export class ProfilPageComponent implements OnInit {
  datauser:any;

  constructor() { }

  ngOnInit(): void {
    let data:any=localStorage.getItem('name');
    this.datauser=JSON.parse(data);
  }
}
