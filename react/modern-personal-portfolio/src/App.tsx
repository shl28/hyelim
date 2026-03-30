import { Navbar } from './components/Navbar';
import { Footer } from './components/Footer';
import { Hero } from './sections/Hero';
import { About } from './sections/About';
import { Portfolio } from './sections/Portfolio';
import { Contact } from './sections/Contact';

export default function App() {
  return (
    <div className="min-h-screen bg-white transition-colors duration-300 dark:bg-zinc-950">
      <Navbar />
      <main>
        <Hero />
        <About />
        <Portfolio />
        <Contact />
      </main>
      <Footer />
    </div>
  );
}
