import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { MyResponse } from 'src/app/my-response';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router:Router,private http:HttpClient) { }

  ngOnInit(): void {
  }
  goToPage(pageName:string):void{
    this.router.navigate([`${pageName}`]);
  }



  loginProduct(addUser: any){
    console.log(addUser);
    this.http.post<MyResponse>('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/User/login',addUser).subscribe((res)=>{
      console.log(res);
      const resId=res;

      if(resId.name===null){
        alert("HIBA");
      }else{
        localStorage.setItem('name',JSON.stringify(res));
        this.router.navigate(["main"]);
      }

    });
  }


}
