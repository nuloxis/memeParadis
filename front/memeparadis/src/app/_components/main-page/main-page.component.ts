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
            <img src="../assets/content/picture/${meme[6]}" style="max-width: 440px;

            width: 100%; max-height:380px; height:100%; margin:10px;"  class="imagememe" alt="">

          `
          loginmeme.innerHTML+=img;
        }
        else{
          const img=`
            <video src="../assets/content/video/${meme[6]}" style="max-width: 440px;

            width: 100%; max-height:380px; margin:10px; height:100%;"  class="imagememe" alt="" controls></video>

          `
          loginmeme.innerHTML+=img;
        }
      }



    })
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
          <img src="../assets/content/picture/${meme[6]}" style="max-width: 440px;
          width: 100%; max-height:380px; height:100%; margin:10px;"  class="imagememe" alt="">
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

            width: 100%; max-height:380px; margin:10px; height:100%;"  class="imagememe" alt="" controls></video>

          `
        loginmeme.innerHTML+=img;

      }
    })
  }

}
