/**
 * Description placeholder
 *
 * @export
 * @param {{text: string}} param0
 * @param {string} param0.text
 * @returns {*}
 */
export default function Label({ text }: {text: string}) {
  
  const lowerCaseText = text.toLowerCase().trim();

  return (
    <label htmlFor={`${lowerCaseText}`} className="text-xs font-normal">{text}</label>
  );
}