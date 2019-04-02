import { Component, OnInit } from '@angular/core';
import { User } from '../model/user'
import { Router } from '@angular/router';
import { UserService } from '../service/user.service'
import * as _ from 'lodash';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  error: boolean = false;
  errorMessage: string = "";
  success: boolean = false;
  successMsg: string = "";
  users: User[];
  uesrsMaster: User[];
  user: User;
  search: string = "";
  sortBy: string = "userId";

  filters = {}

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit() {
    this.init();
  }

  private init() {
    this.user = new User();
    console.log(this.user);
    this.userService.getAllUsers().subscribe(
      result => {
        this.uesrsMaster = result;
        this.users = result;
        //this.applyFilters();
      },
      error => {
        this.error = true;
        this.errorMessage = "Error occured While retriving Users";
      }
    )
  }

  sort(prop: string) {
    this.users = this.users.sort((a, b) => a[prop] > b[prop] ? 1 : a[prop] === b[prop] ? 0 : -1);
  }

  reset() {
    this.user = new User();
  }

  private applyFilters() {
    this.users = _.filter(this.uesrsMaster, _.conforms(this.filters))
  }

  filterContains(value: string) {
    this.filters["firstName"] = val => val.includes(value);
    this.filters["lastName"] = val => val.includes(value);
    this.filters["empId"] = val => val.includes(value);
    this.applyFilters();
  }

  removeFilter(property: string) {
    delete this.filters[property]
    this[property] = null
    this.applyFilters()
  }

  delete(user: User) {
    this.userService.delete(user).subscribe(
      addResult => {
        this.success = true;
        this.init();
        this.successMsg = "User Successfully Deleted"
      },
      error => {
        this.error = true;
        this.errorMessage = "Error occured While Deleting User";
      }
    )
  }

  add() {
    this.userService.create(this.user).subscribe(
      addResult => {
        this.success = true;
        this.init();
        this.successMsg = "User Successfully Added"
      },
      error => {
        this.error = true;
        this.errorMessage = "Error occured While Adding User";
      }
    )
  }

  edit(user: User) {
    this.user = user;
  }

  update() {
    this.userService.update(this.user).subscribe(
      addResult => {
        this.success = true;
        this.init();
        this.successMsg = "User Successfully Updated"
      },
      error => {
        this.error = true;
        this.errorMessage = "Error occured While Updating User";
      }
    )
  }

}
