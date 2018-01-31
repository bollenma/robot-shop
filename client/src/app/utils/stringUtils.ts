import {isNullOrUndefined} from 'util';

export class StringUtils {
  
  static isEmpty(text: string): boolean {
    if (isNullOrUndefined(text)) {
      return true;
    }
    
    text = text.trim();
    return text === '';
  }
}
