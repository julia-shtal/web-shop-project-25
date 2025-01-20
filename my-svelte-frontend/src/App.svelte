<script>
  import { onMount } from 'svelte';
  let products = [];
  let error = null;
  const catalogUrl = 'http://localhost:8080/saas/catalog';

  async function fetchProducts() {
    try {
      const response = await fetch(catalogUrl, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'x-api-key': '550e8400-e29b-41d4-a716-446655440000'
        }
      });
      if (!response.ok) {
        throw new Error(`Error fetching products: ${response.statusText}`);
      }
      products = await response.json();
    } catch (err) {
      error = err.message;
    }
  }

  onMount(() => {
    fetchProducts();
  });
</script>

<main>
  <h1>Product Catalog</h1>

  {#if error}
    <div class="error">
      <p>{error}</p>
    </div>
  {:else if products.length === 0}
    <div class="loading">
      <div class="spinner"></div>
      <p>Loading products...</p>
    </div>
  {:else}
    <div class="product-grid">
      {#each products as product}
        <div class="product-card">
          <h2>{product.name}</h2>
          <p><strong>Color:</strong> {product.color}</p>
          <p><strong>Size:</strong> {product.size}</p>
          <p><strong>Price:</strong> {product.price}$</p>
        </div>
      {/each}
    </div>
  {/if}
</main>

<style>
  main {
    padding: 50px;
    font-family: Arial, sans-serif;
    line-height: 1.6;
  }

  h1 {
    text-align: center;
    margin-bottom: 20px;
  }

  .error {
    color: #fff;
    background-color: #f44336;
    padding: 15px;
    border-radius: 5px;
    text-align: center;
  }

  .loading {
    text-align: center;
  }

  .spinner {
    margin: 0 auto;
    width: 40px;
    height: 40px;
    border: 4px solid #ccc;
    border-top: 4px solid #000;
    border-radius: 50%;
    animation: spin 1s linear infinite;
  }

  @keyframes spin {
    from {
      transform: rotate(0deg);
    }
    to {
      transform: rotate(360deg);
    }
  }

  .product-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 20px;
  }

  .product-card {
    border: 1px solid #ddd;
    border-radius: 8px;
    padding: 15px;
    background-color: #f9f9f9;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s, box-shadow 0.2s;
  }

  .product-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 6px 10px rgba(0, 0, 0, 0.15);
  }

  .product-card h2 {
    margin: 0 0 10px;
    font-size: 1.5em;
  }

  .product-card p {
    margin: 5px 0;
  }
</style>
