import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Robot} from '../../core/robot.model';
import {isNullOrUndefined} from 'util';
import {URL_SERVER, API_ROBOTS} from '../../constants/ConstantsURL';
import 'rxjs/add/operator/map';
import {Page} from '../../core/page.model';
import {Pageable} from '../../core/pageable.model';
import {PageEvent} from '@angular/material';

@Injectable()
export class RobotService {
  
  url = URL_SERVER + API_ROBOTS;
  
  constructor(private http: HttpClient) {
  }
  
  private static handleError(error: any): Observable<any> {
    console.error('An error occurred', error);
    return Observable.throw(error.message || error);
  }
  
  findAllPaginated(pageEvent: PageEvent): Observable<Page<Robot>> {
    const params = {
      params: {
        page: pageEvent.pageIndex.toString(),
        size: pageEvent.pageSize.toString(),
      },
    };
    return this.http.get<Page<Robot>>(this.url, params)
      .map(
        response => response,
        error => RobotService.handleError(error),
      );
  }
  
  find(id: number): Observable<Robot> {
    return this.http.get<Robot>(this.url + '/' + id)
      .map(
        response => response,
        error => RobotService.handleError(error),
      );
  }
  
  findByModelPaginated(model: string, pageEvent: PageEvent): Observable<Page<Robot>> {
    const params = {
      params: {
        page: pageEvent.pageIndex.toString(),
        size: pageEvent.pageSize.toString(),
      },
    };
    return this.http.get(this.url + '/model/' + model, params)
      .map(
        response => response as Page<Robot>,
        error => RobotService.handleError(error),
      );
    
  }
  
  search(search: string, pageEvent: PageEvent): Observable<Page<Robot>> {
    const params = {
      params: {
        query: search,
        page: pageEvent.pageIndex.toString(),
        size: pageEvent.pageSize.toString(),
      },
    };
    return this.http.get(this.url + '/search', params)
      .map(
        response => response as Page<Robot>,
        error => RobotService.handleError(error),
      );
  }
  
  searchWithModel(search: string, model: string, pageEvent: PageEvent): Observable<Page<Robot>> {
    const params = {
      params: {
        query: search,
        model: model,
        page: pageEvent.pageIndex.toString(),
        size: pageEvent.pageSize.toString(),
      },
    };
    return this.http.get(this.url + '/search', params)
      .map(
        response => response as Page<Robot>,
        error => RobotService.handleError(error),
      );
  }
  
  create(robot: Robot): Observable<Robot> {
    return this.http.post(this.url, robot)
      .map(
        response => response as Robot,
        error => RobotService.handleError(error),
      );
  }
  
  update(robot: Robot): Observable<Robot> {
    return this.http.put(this.url, robot)
      .map(
        response => response as Robot,
        error => RobotService.handleError(error),
      );
    
  }
  
  remove(id: number) {
    this.http.delete(this.url + '/' + id).subscribe(
      response => console.log(response),
      error => RobotService.handleError(error),
    );
  }
  
}
