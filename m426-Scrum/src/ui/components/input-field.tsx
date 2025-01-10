import TextInput from "./input";
import Label from "./label";

/**
 * Description placeholder
 *
 * @interface InputProps
 * @typedef {InputProps}
 * @extends {React.InputHTMLAttributes<HTMLInputElement>}
 */
interface InputProps extends React.InputHTMLAttributes<HTMLInputElement> {
  /**
   * Description placeholder
   *
   * @type {string}
   */
  type: string;
  /**
   * Description placeholder
   *
   * @type {?string}
   */
  text?: string;
}

/**
 * Description placeholder
 *
 * @export
 * @param {InputProps} param0
 * @param {*} param0.className
 * @param {string} param0.type
 * @param {string} param0.text
 * @param {{}} param0....props
 * @returns {*}
 */
export default function InputField({ className, type, text, ...props }: InputProps) {
  
  return (
    <fieldset className="flex flex-col items-start justify-center my-1 gap-1 w-full">
      <Label text={type}/>
      <TextInput type={type} text={text} className={className} {...props} />
    </fieldset>
  );
}