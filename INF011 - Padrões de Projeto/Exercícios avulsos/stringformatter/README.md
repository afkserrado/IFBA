# Exercise 1: Simplify a StringFormatter

**Simplify StringFormatter**

**Problem:** A developer built an overengineered string formatting system. There is a `FormatterRegistry` that stores formatters, a `FormatterFactory` that creates them, and a `FormatterChain` that applies them in sequence. All of this just to format a user's display name (trim whitespace and capitalize the first letter).

Your task: replace the entire system with a single `formatDisplayName` method.

Requirements:
- Accept a raw name string (e.g., " john doe ")
- Trim leading and trailing whitespace
- Capitalize the first letter of the result
- Return the formatted string (e.g., "John doe")