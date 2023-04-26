import {EventTypes} from "./eventTypes";

export interface ToastEvent {
  type: EventTypes;
  title: string;
  message: string;
}
