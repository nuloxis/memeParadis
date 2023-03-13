import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationComponent } from '../registration/registration.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }
  goToPage(pageName:string):void{
    this.router.navigate([`${pageName}`]);
  }

}
