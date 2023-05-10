import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private http:HttpClient,private router:Router) {

  }

  ngOnInit(): void {

  }
  goToPage(pageName:string):void{
    this.router.navigate([`${pageName}`]);
  }

  onProductCreate(addNewUser: User){
    const birth :Date=new Date(addNewUser.birthDate);
    const now=new Date();
    const age=now.getFullYear() - birth.getFullYear();
    const myString=addNewUser.password
    const containsUppercase = /[A-Z]/.test(myString);
    const containsNumber = /\d/.test(myString);
    const containsSpecialChar = /[@$!%*?&]/.test(myString);

    if(age>=18){
      this.http.post('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/User/addNewUser',addNewUser,{responseType:'text'}).subscribe((res)=>{
        console.log(res);
        if(addNewUser.name.length<5){
          alert("User name must be at least 5 characters!")
        }
        else if(containsUppercase==false||containsNumber==false||containsSpecialChar==false){
          alert("awdawdaw")
        }
        else if(res="Email is already taken!"){
          alert("Email is already taken!")
        }
      });
    }else{
      alert("birht not correct!")
    }

  }


}
