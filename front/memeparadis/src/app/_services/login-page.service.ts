import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Content } from '../models/content';

@Injectable({
  providedIn: 'root'
})
export class LoginRegistrationPageService {



  apiUrl = "http://127.0.0.1:8080/MemeparadisEE7-1.0-SNAPSHOT/resources";

  constructor(private http: HttpClient) { }

  getMostLikedPosts(): Observable<Content[]>{
    return this.http.get<Content[]>(`${this.apiUrl}/Content/GetMostLikedPosts`)
  }
  showImage(url: string) {
    // Megjelenítjük a képet a képernyő közepén
    const imgElement = document.createElement('img');
    imgElement.src = url;
    imgElement.style.position = 'fixed';
    imgElement.style.top = '50%';
    imgElement.style.left = '50%';
    imgElement.style.transform = 'translate(-50%, -50%)';
    imgElement.style.maxWidth = '90%';
    imgElement.style.maxHeight = '90%';
    imgElement.style.zIndex = '9999';
    document.body.appendChild(imgElement);
  }

}
