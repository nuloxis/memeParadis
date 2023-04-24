import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  datauser:any;

  constructor() { }

  ngOnInit(): void {
    let data:any=localStorage.getItem('name');
    this.datauser=JSON.parse(data);
  }

}
