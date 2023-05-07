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

    const editingnamebutton = document.getElementById("edditnamebutton") as HTMLButtonElement;
    const editinglabel=document.getElementById("editinglabel") as HTMLLabelElement;
    const edditname=document.getElementById("edditname") as HTMLInputElement;
    let isEditing = false;

    editingnamebutton.onclick = function() {
      if (!isEditing) {
        editinglabel.style.display = "none";
        edditname.style.display = "block";
        isEditing = true;
      } else {
        edditname.style.display = "none";
        editinglabel.style.display = "contents";
        isEditing = false;
      }
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
  async creatContentfunctione(){
    let howmanycontent:number=0 ;

    this.http.get<number>('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/Content/getHowManyContent').subscribe(res=>{
        howmanycontent=res;

    })
    await new Promise(resolve => setTimeout(resolve, 800));


    const content2: Content = {id:0,adultContent:false,uploaderName:0,language:"",likes:0,contentType:false,contentUpladeName:"",};
    const selectmenu2=document.getElementById("format") as HTMLSelectElement;
    const language_select=document.getElementById("language-select") as HTMLSelectElement;


    let data:any=localStorage.getItem('name');
    this.datauser=JSON.parse(data);
    content2.uploaderName=this.datauser.id;


    const filedata=this.file;
    var myFormData = new FormData();
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');
    myFormData.append('video', filedata);



    if(selectmenu2.value=="video/*"){
      content2.contentType=true;
      this.http.post('http://localhost/saves.php', myFormData, {
        headers: headers,
      }).subscribe(data => {

        console.log(data);
      },err=>{
        console.log("")
      });
      await new Promise(resolve => setTimeout(resolve, 800));
    }
    else if(selectmenu2.value=="image/*"){
      content2.contentType=false;
      this.http.post('http://localhost/saves2.php', myFormData, {
        headers: headers,
      }).subscribe(data => {

        console.log(data);
      },err=>{
        console.log("")
      });
      await new Promise(resolve => setTimeout(resolve, 800));
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


    const buttonTexts = this.getButtonTexts();
    await new Promise(resolve => setTimeout(resolve, 800));


    for (const buttonText of buttonTexts) {
      let jsonStr = `{"tag": "${buttonText}"}`;
      let obj = JSON.parse(jsonStr);
      let tagsid:number|null=0;
      this.http.post<number>('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/Tags/createTag',obj).subscribe((res)=>{
        console.log(res)
        tagsid=res;
      });
      await new Promise(resolve => setTimeout(resolve, 800));
      let content_tag=`{
        "contentId": ${howmanycontent},
        "tagsId": ${tagsid}
      }`
      let sendcreatecontent_tag=JSON.parse(content_tag);
      console.log(sendcreatecontent_tag);
      this.http.post('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/ContentTag/createContent_tag',sendcreatecontent_tag).subscribe((res)=>{
        console.log(res);

      });
      await new Promise(resolve => setTimeout(resolve, 800));
      jsonStr = "";
      obj = null;
      tagsid=null;
      content_tag="";

      console.log(jsonStr);
      console.log(obj)

    }

    /**/


    this.http.post('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/Content/createContent',content2).subscribe((res)=>{
      console.log(res);
    },err=>{
      console.log("")
    })
    await new Promise(resolve => setTimeout(resolve, 800));

  }

  getFile(event:any){
    const file: File = event.currentTarget.files[0];
    this.file=event.currentTarget.files[0];
    const filename2=file.name;
    this.filename=filename2


  }
  getButtonTexts() {
    const tagDiv = document.getElementById("tagdiv")  as HTMLDivElement;
    const buttonTexts = Array.from(tagDiv.querySelectorAll("button")).map((button) => {
      return button.innerText;
    });
    return buttonTexts;
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

