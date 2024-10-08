/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./**/*.jsp", "./**/*.html"],
  theme: {
    extend: {},
  },
  plugins: [
    function ({ addVariant }) {
      addVariant("child", "& > *");
    },
  ],
};
