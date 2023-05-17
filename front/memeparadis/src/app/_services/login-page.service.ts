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


}
