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

  onProductCreate(uservalues: {name:string,email:string,passworld:string,birth:Date}){
    console.log(uservalues);
    this.http.post('',uservalues).subscribe((res)=>{
      console.log(res);
    });
  }

}
