import { Component, OnInit } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Content } from 'src/app/models/content';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  content!:Content
  datauser:any;
  router: any;

  constructor(private http:HttpClient) { }

  ngOnInit(): void {


    const loginmeme=document.getElementById("memes") as HTMLDivElement;
    let data:any=localStorage.getItem('name');
    this.datauser=JSON.parse(data);
    this.http.get<Content>('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/Content/getAllContentRand').subscribe(res=>{
      this.content=res;


      for(const meme of Object.values(res)){


        if (meme[5]==false){
          const img=`
            <img src="../assets/content/picture/${meme[6]}" onclick="showImage('../assets/content/picture/${meme[6]}')"  style="max-width: 440px ;

            width: 100%; max-height:380px; height:100%; margin:10px; cursor: pointer; border-radius: 5px;  "  class="imagememe" alt="" onmouseover="this.style.filter='brightness(0.8)'"
            onmouseout="this.style.filter='brightness(1)'">

          `
          loginmeme.innerHTML+=img;
        }
        else{
          const img=`
            <video src="../assets/content/video/${meme[6]}" style="max-width: 440px;

            width: 100%; max-height:380px; margin:10px; height:100%; border-radius: 5px;"  class="imagememe" alt="" controls onmouseover="this.style.filter='brightness(0.8)'"
            onmouseout="this.style.filter='brightness(1)'"></video>

          `
          loginmeme.innerHTML+=img;
        }
      }



    })






  }
  searchcontent(){

    const inputtagss=document.getElementById("inputtagss") as HTMLInputElement;
    const valuetag=inputtagss.value;
    if(valuetag!=""){
      const loginmeme=document.getElementById("memes") as HTMLDivElement;
      while (loginmeme.firstChild) {
        loginmeme.removeChild(loginmeme.firstChild);
      }
      this.http.get<Content>(`http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/Content/getContentBytag/${valuetag}`).subscribe(res=>{
      this.content=res;
      for(const meme of Object.values(res)){


        if (meme[5]==false){
          const img=`
            <img src="../assets/content/picture/${meme[6]}" onclick="showImage('../assets/content/picture/${meme[6]}')"  style="max-width: 440px ;

            width: 100%; max-height:380px; height:100%; margin:10px; cursor: pointer; border-radius: 5px;  "  class="imagememe" alt="" onmouseover="this.style.filter='brightness(0.8)'"
            onmouseout="this.style.filter='brightness(1)'">

          `
          loginmeme.innerHTML+=img;
        }
        else{
          const img=`
            <video src="../assets/content/video/${meme[6]}" style="max-width: 440px;

            width: 100%; max-height:380px; margin:10px; height:100%; border-radius: 5px;"  class="imagememe" alt="" controls onmouseover="this.style.filter='brightness(0.8)'"
            onmouseout="this.style.filter='brightness(1)'"></video>

          `
          loginmeme.innerHTML+=img;
        }
      }
      inputtagss.value="";
    },err=>{
      alert("Ilyen nincs");
      console.clear();
      inputtagss.value="";


    })
    }



  }

  goToPage(pageName:string):void{
    this.router.navigate([`${pageName}`]);
  }


  loadallpicture():void{
    const loginmeme=document.getElementById("memes") as HTMLDivElement;
    while (loginmeme.firstChild) {
      loginmeme.removeChild(loginmeme.firstChild);
    }

    this.http.get<Content>('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/Content/getPictures').subscribe(res=>{
      this.content=res;


      for(const meme of Object.values(res)){
        const img=`
          <img src="../assets/content/picture/${meme[6]}" onclick="showImage('../assets/content/picture/${meme[6]}')" style="max-width: 440px;
          width: 100%; max-height:380px; height:100%; margin:10px; cursor: pointer; border-radius: 5px;"  class="imagememe" alt="" onmouseover="this.style.filter='brightness(0.8)'"
          onmouseout="this.style.filter='brightness(1)'">
        `
        loginmeme.innerHTML+=img;

      }
    })
  }

  loadallvideo():void{
    const loginmeme=document.getElementById("memes") as HTMLDivElement;
    while (loginmeme.firstChild) {
      loginmeme.removeChild(loginmeme.firstChild);
    }

    this.http.get<Content>('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/Content/getVideos').subscribe(res=>{
      this.content=res;

      for(const meme of Object.values(res)){
        const img=`
            <video src="../assets/content/video/${meme[6]}" style="max-width: 440px;

            width: 100%; max-height:380px; margin:10px; height:100%; border-radius: 5px;"  class="imagememe" alt="" controls onmouseover="this.style.filter='brightness(0.8)'"
            onmouseout="this.style.filter='brightness(1)'"></video>

          `
        loginmeme.innerHTML+=img;

      }
    })
  }
  openpicture():void{
    console.log("asdasd")
  }
  loadEnglish():void{

    const loginmeme=document.getElementById("memes") as HTMLDivElement;
    while (loginmeme.firstChild) {
      loginmeme.removeChild(loginmeme.firstChild);
    }
    this.http.get<Content>('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/Content/getEnglishContents').subscribe(res=>{
      this.content=res;


      for(const meme of Object.values(res)){


        if (meme[5]==false){
          const img=`
            <img src="../assets/content/picture/${meme[6]}" onclick="showImage('../assets/content/picture/${meme[6]}')"  style="max-width: 440px ;

            width: 100%; max-height:380px; height:100%; margin:10px; cursor: pointer; border-radius: 5px;"  class="imagememe" alt="" (click)="openpicture()" onmouseover="this.style.filter='brightness(0.8)'"
            onmouseout="this.style.filter='brightness(1)'">

          `
          loginmeme.innerHTML+=img;
        }
        else{
          const img=`
            <video src="../assets/content/video/${meme[6]}" style="max-width: 440px;

            width: 100%; max-height:380px; margin:10px; height:100%; border-radius: 5px;"  class="imagememe" alt="" controls onmouseover="this.style.filter='brightness(0.8)'"
            onmouseout="this.style.filter='brightness(1)'"></video>

          `
          loginmeme.innerHTML+=img;
        }
      }



    })
  }

  loadHungary():void{

    const loginmeme=document.getElementById("memes") as HTMLDivElement;
    while (loginmeme.firstChild) {
      loginmeme.removeChild(loginmeme.firstChild);
    }
    this.http.get<Content>('http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources/Content/getHungarianContents').subscribe(res=>{
      this.content=res;


      for(const meme of Object.values(res)){


        if (meme[5]==false){
          const img=`
            <img src="../assets/content/picture/${meme[6]}" onclick="showImage('../assets/content/picture/${meme[6]}')"  style="max-width: 440px ;

            width: 100%; max-height:380px; height:100%; margin:10px; cursor: pointer; border-radius: 5px;"  class="imagememe" alt="" (click)="openpicture()" onmouseover="this.style.filter='brightness(0.8)'"
            onmouseout="this.style.filter='brightness(1)'">

          `
          loginmeme.innerHTML+=img;
        }
        else{
          const img=`
            <video src="../assets/content/video/${meme[6]}" style="max-width: 440px;

            width: 100%; max-height:380px; margin:10px; height:100%; border-radius: 5px;"  class="imagememe" alt="" controls onmouseover="this.style.filter='brightness(0.8)'"
            onmouseout="this.style.filter='brightness(1)'"></video>

          `
          loginmeme.innerHTML+=img;
        }
      }



    })
  }



}
