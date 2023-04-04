import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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

  onProductCreate(addNewUser: any){
    console.log(addNewUser);
    this.http.post('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/User/addNewUser',addNewUser).subscribe((res)=>{
      console.log(res);
    });
  }


}
