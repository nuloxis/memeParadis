import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profil-page',
  templateUrl: './profil-page.component.html',
  styleUrls: ['./profil-page.component.css']
})
export class ProfilPageComponent implements OnInit {
  datauser:any;

  constructor() { }

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
  }

}
