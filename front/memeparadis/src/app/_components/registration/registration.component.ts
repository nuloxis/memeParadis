import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private http:HttpClient) {

  }

  ngOnInit(): void {
  }

  onProductCreate(addNewUser: {name:string,email:string,password:string,birthDate:Date}){
    console.log(addNewUser);
    this.http.post('http://127.0.0.1:8080/memeparadisBack-1.0-SNAPSHOT/javaee8/User/addnewUser',JSON.stringify(addNewUser)).subscribe((res)=>{
      console.log(res);
    });
  }

}
