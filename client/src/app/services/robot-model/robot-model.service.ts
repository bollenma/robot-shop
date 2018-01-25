import {Injectable} from '@angular/core';
import {RobotModel} from '../../core/robot-model.model';
import {Observable} from 'rxjs/Observable';
import {URL_SERVER, API_ROBOTS} from '../../constants/ConstantsURL';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';

@Injectable()
export class RobotModelService {
  
  url = URL_SERVER + API_ROBOTS;
  
  constructor(private http: HttpClient) {
  }
  
  private static handleError(error: any): Observable<any> {
    console.error('An error occurred', error);
    return Observable.throw(error.message || error);
  }
  
  findAll(): Observable<RobotModel[]> {
    return this.http.get(this.url + '/model-list').map(
        response => response as RobotModel[],
        error => RobotModelService.handleError(error),
      );
  }
  
}
