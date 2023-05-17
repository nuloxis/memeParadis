import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { MyResponse } from 'src/app/my-response';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ObserversModule } from '@angular/cdk/observers';
import { Content } from '../../models/content';
import { LoginRegistrationPageService } from 'src/app/_services/login-page.service';
import * as fs from 'fs';
import { MatDialog } from '@angular/material/dialog';
import { LoginalertComponent } from 'src/app/pop_up/loginalert/loginalert.component';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  content!:Content;



  constructor(private router:Router,private http:HttpClient, private loginservices:LoginRegistrationPageService, private matdialog:MatDialog) { }


  ngOnInit(): void {

    const loginmeme=document.getElementById("loginmeme") as HTMLDivElement;

    this.http.get<Content>('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/Content/GetMostLikedPosts').subscribe(
      res=>{
        this.content=res;

        if (this.content.contentType==false){
          const img=`
            <img src="../assets/content/picture/${this.content.contentUpladeName}" onclick="showImage('../assets/content/picture/${this.content.contentUpladeName}')" style="max-width: 678px;
            height: auto;
            width: 100%; max-height:515px; cursor: pointer;   class="imagememe" alt="" onmouseover="this.style.filter='brightness(0.8)'"
            onmouseout="this.style.filter='brightness(1)'">

          `
          loginmeme.innerHTML+=img;
        }
        else{
          const img=`
            <img src="../assets/content/video/${this.content.contentUpladeName}" class="imagememe" style="height: 678px; width: 676px;" alt="" onmouseover="this.style.filter='brightness(0.8)'"
            onmouseout="this.style.filter='brightness(1)'">

          `
          loginmeme.innerHTML+=img;
        }

      }
    )

  }


  goToPage(pageName:string):void{
    this.router.navigate([`${pageName}`]);
  }


  loginProduct(addUser: any){

    this.http.post<MyResponse>('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/User/login',addUser).subscribe((res)=>{
      const resId=res;

      if(resId.name===null){
        this.opendialog()
      }else{
        localStorage.setItem('name',JSON.stringify(res));
        this.router.navigate(["main"]);
      }

    });
  }

  opendialog(){
    this.matdialog.open(LoginalertComponent);
  }


}
