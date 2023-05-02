import {  OnInit } from '@angular/core';

import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {Component, ElementRef, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {MatChipInputEvent} from '@angular/material/chips';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';



@Component({
  selector: 'app-profil-page',
  templateUrl: './profil-page.component.html',
  styleUrls: ['./profil-page.component.css']
})
export class ProfilPageComponent implements OnInit {
  datauser:any;


  separatorKeysCodes: number[] = [ENTER, COMMA];
  fruitCtrl = new FormControl('');
  filteredFruits: Observable<string[]>;
  fruits: string[] = ['Lemon'];
  allFruits: string[] = ['Apple', 'Lemon', 'Lime', 'Orange', 'Strawberry'];

  @ViewChild('fruitInput') fruitInput!: ElementRef<HTMLInputElement>;

  constructor() {
    this.filteredFruits = this.fruitCtrl.valueChanges.pipe(
      startWith(null),
      map((fruit: string | null) => (fruit ? this._filter(fruit) : this.allFruits.slice())),
    );
  }
  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.fruits.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();

    this.fruitCtrl.setValue(null);
  }

  remove(fruit: string): void {
    const index = this.fruits.indexOf(fruit);

    if (index >= 0) {
      this.fruits.splice(index, 1);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.fruits.push(event.option.viewValue);
    this.fruitInput.nativeElement.value = '';
    this.fruitCtrl.setValue(null);
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.allFruits.filter(fruit => fruit.toLowerCase().includes(filterValue));
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

