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
  public hiddenPassword: string = '';




  constructor(private http:HttpClient) {

  }


  ngOnInit(): void {
    let data:any=localStorage.getItem('name');
    this.datauser=JSON.parse(data);
    const userid=this.datauser.id;
    const username=this.datauser.name;
    const useremail=this.datauser.email;

    this.hiddenPassword = '*'.repeat(this.datauser.password.length);


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
    const updatename :any={name:"",id:0};
    updatename.id=userid;

    const user = JSON.parse(localStorage.getItem("name")!);


    editingnamebutton.onclick = ()=> {

      if (!isEditing) {
        editinglabel.style.display = "none";
        edditname.style.display = "block";
        isEditing = true;
        edditname.value=username;
        editingnamebutton.textContent="Send!";
      } else {
        edditname.style.display = "none";
        editinglabel.style.display = "contents";
        isEditing = false;
        editingnamebutton.textContent="Editing";
        updatename.name=edditname.value;
        if(edditname.value!=username){
          this.editusername(updatename);
          user.name=edditname.value;
          localStorage.removeItem("name");
          localStorage.setItem("name", JSON.stringify(user));
          setTimeout(()=>{
            window.location.reload()
          }, 500);
        }
      }
    }

    const editingnamebutton2 = document.getElementById("edditemailbutton2") as HTMLButtonElement;
    const editinglabel2=document.getElementById("editinglabel2") as HTMLLabelElement;
    const edditname2=document.getElementById("edditemail") as HTMLInputElement;
    let isEditing2 = false;
    const updateemail :any={email:"",id:0};
    updateemail.id=userid;



    editingnamebutton2.onclick = ()=> {

      if (!isEditing2) {
        editinglabel2.style.display = "none";
        edditname2.style.display = "block";
        isEditing2 = true;
        edditname2.value=useremail;
        editingnamebutton2.textContent="Send!";
      } else {
        edditname2.style.display = "none";
        editinglabel2.style.display = "contents";
        isEditing2 = false;
        editingnamebutton2.textContent="Editing";
        updateemail.email=edditname2.value;
        if(edditname2.value!=useremail){
          this.edituseremail(updateemail);
          user.email=edditname2.value;
          localStorage.removeItem("name");
          localStorage.setItem("name", JSON.stringify(user));
          setTimeout(()=>{
            window.location.reload()
          }, 500);
        }
      }
    }


    const edditemailbutton3 = document.getElementById("edditemailbutton3") as HTMLButtonElement;
    const editinglabel3=document.getElementById("editinglabel3") as HTMLLabelElement;
    const labeltext=document.getElementById("labeltext") as HTMLLabelElement;
    const labeltext2=document.getElementById("labeltext2") as HTMLLabelElement;
    const edditpasswordold=document.getElementById("edditpasswordold") as HTMLInputElement;
    const edditpasswordnew=document.getElementById("edditpasswordnew") as HTMLInputElement;

    let isEditing3 = false;
    const updatepassword :any={currentPw:"",newPw:"",id:0};
    updatepassword.id=userid;



    edditemailbutton3.onclick = async ()=> {

      if (!isEditing3) {
        editinglabel3.style.display = "none";
        edditpasswordold.style.display = "block";
        edditpasswordnew.style.display = "block";
        labeltext.style.display = "block";
        labeltext2.style.display = "block";
        isEditing3 = true;
        edditemailbutton3.textContent="Send!";
      } else {
        editinglabel3.style.display = "contents";
        edditpasswordold.style.display = "none";
        edditpasswordnew.style.display = "none";
        labeltext.style.display = "none";
        labeltext2.style.display = "none";
        isEditing3 = false;
        edditemailbutton3.textContent="Editing";
        updatepassword.currentPw=edditpasswordold.value;
        updatepassword.newPw=edditpasswordnew.value;
        if(edditpasswordold.value!="" && edditpasswordnew.value!=""){
          try {
            this.http.put('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/User/updatePassword',updatepassword,{responseType:'text'}).subscribe((res)=>{
              console.log(res);
              if(res=="A jelenlegi jelszó vagy az új jelszó nem megfelelő."){
                alert("Error!");
              }
              else{
                alert("Successful password change!");
                this.logoutfuntion();
              }

            })

          } catch (error) {
            alert("Error!");
          }


        }
      }
    }



    const selectmenu2=document.getElementById("format") as HTMLSelectElement;
    const fileinput=document.getElementById("file") as HTMLInputElement;


    fileinput.onclick=function(){

      if(selectmenu2.value=="video/*"){
        fileinput.accept="";
        fileinput.accept="video/*";

      }
      else if(selectmenu2.value=="image/*"){
        fileinput.accept="";
        fileinput.accept="image/*";

      }
      else{


      }
    }


    const addtagsbutton=document.getElementById("addtagsbutton") as HTMLButtonElement;
    const inputtags=document.getElementById("inputtags")as HTMLInputElement;
    const tagdiv=document.getElementById("tagdiv")as HTMLDivElement;
    const clearbutton=document.getElementById("clearbutton") as HTMLButtonElement;

    addtagsbutton.onclick=function(){
      const tag=inputtags.value;
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



  };
  editusername(user:any){
    this.http.put('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/User/updateUserName',user).subscribe(res=>{
    })
  };
  edituseremail(email:any){
    this.http.put('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/User/updateEmail',email).subscribe()
  };
  edituserpassword(password:any){
    this.http.put('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/User/updatePassword',password).subscribe()
  };
  logoutfuntion():void{
    localStorage.clear();
    setTimeout(()=>{
      window.location.reload()
    }, 200);
  }
  async creatContentfunctione(){

    try {
      let howmanycontent:number=0 ;
      const content2: Content = {adultContent:false,uploaderName:0,language:"",contentType:false,contentUpladeName:""};
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
      myFormData.append('picture', filedata);
      const fileinput=document.getElementById("file") as HTMLInputElement;

      if(selectmenu2.value=="video/*"){
        content2.contentType=true;
        this.http.post('http://localhost/saves.php', myFormData, {
          headers: headers,

        }).subscribe();
        console.clear();

      }
      else if(selectmenu2.value=="image/*"){

        content2.contentType=false;
        this.http.post('http://localhost/saves2.php', myFormData, {
          headers: headers,
        }).subscribe();
        console.clear();


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
      this.http.post('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/Content/createContent',content2,{responseType:'text'}).subscribe((res)=>{
        const id = parseInt(res.split(': ')[1]);
        howmanycontent=id;

      })
      await new Promise(resolve => setTimeout(resolve, 800));

      const buttonTexts = this.getButtonTexts();
      for (const buttonText of buttonTexts) {
        let jsonStr = `{"tag": "${buttonText}"}`;
        let obj = JSON.parse(jsonStr);
        let tagsid:number|null=0;
        this.http.post<number>('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/Tags/createTag',obj).subscribe((res)=>{
          tagsid=res;
        });
        await new Promise(resolve => setTimeout(resolve, 800));
        let content_tag=`{
          "contentId": ${howmanycontent},
          "tagsId": ${tagsid}
        }`
        let sendcreatecontent_tag=JSON.parse(content_tag);

        this.http.post('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/ContentTag/createContent_tag',sendcreatecontent_tag,{responseType:'text'}).subscribe((res)=>{
        });
        await new Promise(resolve => setTimeout(resolve, 800));
        jsonStr = "";
        obj = null;
        tagsid=null;
        content_tag="";

      }


      const parentElement = document.getElementById("tagdiv");
        while (parentElement!.firstChild) {
          parentElement!.removeChild(parentElement!.firstChild);
        }
      selectmenu2.options[0].selected = true;
      language_select.options[0].selected=true;


      alert("Successful upload!")
      console.clear();
    } catch (error) {
      alert("Some required fields are missing!")
    }





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


  loaduploadedconten():void{
    let data:any=localStorage.getItem('name');
    this.datauser=JSON.parse(data);
    const userid=this.datauser.id;


    const loginmeme=document.getElementById("uploadedcontent") as HTMLDivElement;
    while (loginmeme.firstChild) {
      loginmeme.removeChild(loginmeme.firstChild);
    }
    this.http.get<Content>(`http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/User/getContentByUserId/${userid}`).subscribe(res=>{
      this.content=res;


      for(const meme of Object.values(res)){


        if (meme[5]==false){
          const img=`
            <img src="../assets/content/picture/${meme[6]}"  style="max-width: 540px ;

            width: 100%; max-height:480px; height:100%; margin:15px;"  class="imagememe" alt="" (click)="openpicture()">

          `
          loginmeme.innerHTML+=img;
        }
        else{
          const img=`
            <video src="../assets/content/video/${meme[6]}" style="max-width: 540px;

            width: 100%; max-height:480px; margin:15px; height:100%;"  class="imagememe" alt="" controls></video>

          `
          loginmeme.innerHTML+=img;
        }
      }



    })
  }



}

