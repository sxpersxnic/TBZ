import { SetStateAction, useState } from "react";

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
export default function TextInput({ className, type, text, ...props }: InputProps ) {
  const [value, setValue] = useState(text || '');

  const handleChange = (e: { target: { value: SetStateAction<string>; }; }) => {
    setValue(e.target.value);
  };

  const lowerCaseType = type.toLowerCase().trim();
  return (
    <input 
      id={`${lowerCaseType}`}
      name={`${lowerCaseType}`}
      type="text"
      placeholder={`Enter ${type}`}
      className={`${className} appearance-none text-sm border-2 border-[--foreground] bg-[--background] text-[--foreground] rounded-md w-full leading-tight placeholder:text-[--foreground] focus:placeholder:text-gray-500`}
      onChange={handleChange}
      defaultValue={value}
      {...props}
    />
    
  );
}