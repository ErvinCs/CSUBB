import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class AuthenticationService {
  apiUrl = '/api/authenticate';

  constructor(private httpClient: HttpClient) { }

  // Make a local storage file which will contain the current user. If the user is not defined in the api,
  // then we won't have anything written to the local storage.
  login(email: string, password: string) {
    return this.httpClient.post(this.apiUrl,
      {
        email: email,
        password: password
      }).map(person  => {
		    console.log(person['token']);
        // person.token = person['token'];
        // if (person && person.token) {
        //   localStorage.setItem('currentUser', JSON.stringify(person));
        // }
        return person;
    });
  }

  // To remove a session, we remove the current user from the file written at the log in segment.
  logout() {
    localStorage.removeItem('currentUser');
  }

}
