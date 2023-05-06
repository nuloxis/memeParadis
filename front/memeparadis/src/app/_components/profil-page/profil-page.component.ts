import { HttpClient,HttpHeaders } from '@angular/common/http';
import {  OnInit } from '@angular/core';
import {Component} from '@angular/core';
import { Content } from 'src/app/models/content';
import {NgForm} from '@angular/forms';



@Component({
  selector: 'app-profil-page',
  templateUrl: './profil-page.component.html',
  styleUrls: ['./profil-page.component.css']
})
export class ProfilPageComponent implements OnInit {
  datauser:any;
  file:any;

  content!:Content;
  filename!:String

  constructor(private http:HttpClient) {

  }


  ngOnInit(): void {
    let data:any=localStorage.getItem('name');
    this.datauser=JSON.parse(data);



    const modal = document.getElementById("profilesettingsmodal") as HTMLElement;
    const btn = document.getElementById("profilesettings") as HTMLButtonElement;

    const modal2 = document.getElementById("contentuploadmodul") as HTMLElement;
    const btn2 = document.getElementById("contentupload") as HTMLButtonElement;

    const modal3 = document.getElementById("likesmodul") as HTMLElement;
    const btn3 = document.getElementById("likescontent") as HTMLButtonElement;
    btn.onclick = function() {
      modal.style.display = "block";
      modal2.style.display = "none";
      modal3.style.display="none";
    }
    btn2.onclick = function() {
      modal.style.display = "none";
      modal2.style.display = "block";
      modal3.style.display="none";
    }
    btn3.onclick = function() {
      modal.style.display = "none";
      modal2.style.display = "none";
      modal3.style.display="block";
    }



    const selectmenu2=document.getElementById("format") as HTMLSelectElement;
    const fileinput=document.getElementById("file") as HTMLInputElement;


    fileinput.onclick=function(){

      if(selectmenu2.value=="video/*"){
        fileinput.accept="";
        fileinput.accept="video/*";
        console.log(fileinput.accept.valueOf())
      }
      else if(selectmenu2.value=="image/*"){
        fileinput.accept="";
        fileinput.accept="image/*";
        console.log(fileinput.accept.valueOf())
      }
      else{
        console.log(fileinput.textContent);

      }
    }


    const addtagsbutton=document.getElementById("addtagsbutton") as HTMLButtonElement;
    const inputtags=document.getElementById("inputtags")as HTMLInputElement;
    const tagdiv=document.getElementById("tagdiv")as HTMLDivElement;
    const clearbutton=document.getElementById("clearbutton") as HTMLButtonElement;

    addtagsbutton.onclick=function(){
      const tag=inputtags.value;
      console.log(tag);
      const createlab=`
      <label class="tagdi2v">
        <button class="deleteButton2" onclick="this.parentElement.remove()">${tag}</button>
      </label>


      `;
      tagdiv.innerHTML +=createlab;
      inputtags.value="";

    }

    clearbutton.onclick=function(){
      const parentElement = document.getElementById("tagdiv");
      while (parentElement!.firstChild) {
        parentElement!.removeChild(parentElement!.firstChild);
      }
    }



  }
  logoutfuntion():void{
    localStorage.clear();
    setTimeout(()=>{
      window.location.reload()
    }, 100);
  }
  creatContentfunctione(){

    const content2: Content = {id:0,adultContent:false,uploaderName:0,language:"",likes:0,contentType:false,contentUpladeName:"",};

    const selectmenu2=document.getElementById("format") as HTMLSelectElement;
    const language_select=document.getElementById("language-select") as HTMLSelectElement;
    const fileid=document.getElementById("file") as HTMLInputElement;


    let data:any=localStorage.getItem('name');
    this.datauser=JSON.parse(data);
    content2.uploaderName=this.datauser.id;






    if(selectmenu2.value=="video/*"){
      content2.contentType=true;
    }
    else if(selectmenu2.value=="image/*"){
      content2.contentType=false;
    }

    if (language_select.value=="ENG"){
      content2.language="ENG";
    }
    else if(language_select.value=="HUN"){
      content2.language="HUN";
    }else{
      content2.language="OTHER";
    }
    content2.contentUpladeName+=this.file.name;


    const filedata=this.file;
    var myFormData = new FormData();
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');
    myFormData.append('video', filedata);

    this.http.post('http://localhost/saves.php', myFormData, {
    headers: headers,
    }).subscribe(data => {

     /*console.log(data);*/
    });


    this.http.post('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/Content/createContent',content2).subscribe((res)=>{
      /*console.log(res);*/
    })

  }

  getFile(event:any){
    const file: File = event.currentTarget.files[0];
    this.file=event.currentTarget.files[0];
    const filename2=file.name;
    this.filename=filename2


  }

  generateRandomString(length: number): string {
    const chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    let result = "";
    for (let i = 0; i < length; i++) {
      result += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return result;
  }
  generateRandomNumber(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1) + min);
  }

  /*<span class="tagSpan" >${tag} <button class="deleteButton2" (click)="deleteButton222()" >Delete</button></span>*/
  /*deleteButton222():void{
    console.log("deleteButton2")
    const deleteButton2 = document.querySelectorAll<HTMLButtonElement>('.deleteButton2');
    deleteButton2.forEach((button) => {
      console.log("asfdas");
      button.addEventListener("click", () => {
        const tagSpan = button.parentElement!;
        tagSpan.remove();
      });
    });
  }*/

/*
  selectmenuasd(): void{
    console.log("sadasd")

    const selectmenu2=document.getElementById("format") as HTMLSelectElement;
    const fileinput=document.getElementById("file") as HTMLInputElement;


    if(selectmenu2.value=="video/*"){
      fileinput.accept="video/*";
      console.log(fileinput.accept.valueOf())


    }
    else if(selectmenu2.value=="image/*"){

      fileinput.accept="";
      fileinput.accept="image/*";
    }

  }
*/






}

