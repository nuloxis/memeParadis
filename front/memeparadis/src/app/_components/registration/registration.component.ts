import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { MatDialog } from '@angular/material/dialog';
import { RegistrationalertComponent } from 'src/app/pop_up/registrationalert/registrationalert.component';
import { RegistrationalertElseComponent } from 'src/app/pop_up/registrationalert-else/registrationalert-else.component';
import { RegistrationbirthalertComponent } from 'src/app/pop_up/registrationbirthalert/registrationbirthalert.component';
import { RegistrationemialalertComponent } from 'src/app/pop_up/registrationemialalert/registrationemialalert.component';
import { RegistrationpasswordalertComponent } from 'src/app/pop_up/registrationpasswordalert/registrationpasswordalert.component';
import { FormBuilder, FormGroup } from '@angular/forms';
import { RegistrationsuccesfulComponent } from 'src/app/pop_up/registrationsuccesful/registrationsuccesful.component';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {




  constructor(private http:HttpClient,private router:Router, private matdialog:MatDialog,private fb: FormBuilder) {

  }
  productsFrom:FormGroup = this.fb.group({
    name: [''],
    email: [''],
    password: [''],
    birthDate: ['']
  })


  ngOnInit(): void {

  }
  goToPage(pageName:string):void{
    this.router.navigate([`${pageName}`]);
  }

  onProductCreate(){

    const addNewUser:User=this.productsFrom.value;

    addNewUser.email=this.productsFrom.get("email")?.value;

    const birth :Date=new Date(addNewUser.birthDate);
    const now=new Date();
    const age=now.getFullYear() - birth.getFullYear();
    const myString=addNewUser.password
    const containsUppercase = /[A-Z]/.test(myString);
    const containsNumber = /\d/.test(myString);
    const containsSpecialChar = /[@$!%*?&]/.test(myString);



    if(addNewUser.name==""||addNewUser.email==""||addNewUser.password==""||Number.isNaN(addNewUser.birthDate)){
      this.opendialog();
    }
    else if(addNewUser.name.length<5){
      this.opendialogelse();
    }
    else if(containsUppercase==false||containsNumber==false||containsSpecialChar==false){
      this.opendialogelsepassword();
    }
    else if(age<18){
      this.opendialogelsebirth();

    }

    else if(age>=18){

      this.http.post('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/User/addNewUser',addNewUser,{responseType:'text'}).subscribe((res)=>{
        if(res=="Email is already taken!"){
          this.opendialogelseemail();
        }
        else{
          this.opendialogelsuccesful();
          this.goToPage("login")
        }

      });
    }



  }
  opendialog(){
    this.matdialog.open(RegistrationalertComponent);
  }
  opendialogelse(){
    this.matdialog.open(RegistrationalertElseComponent)
  }
  opendialogelsebirth(){
    this.matdialog.open(RegistrationbirthalertComponent)
  }
  opendialogelseemail(){
    this.matdialog.open(RegistrationemialalertComponent)
  }
  opendialogelsepassword(){
    this.matdialog.open(RegistrationpasswordalertComponent)
  }
  opendialogelsuccesful(){
    this.matdialog.open(RegistrationsuccesfulComponent)
  }


}
